package com.example.filestringgenerator.service;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.domain.State;
import com.example.filestringgenerator.domain.StringGeneration;
import com.example.filestringgenerator.dto.PayloadDTO;
import com.example.filestringgenerator.dto.PayloadIdleResponseDTO;
import com.example.filestringgenerator.dto.PayloadPostReferenceDTO;
import com.example.filestringgenerator.mapper.PayloadMapper;
import com.example.filestringgenerator.repository.PayloadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayloadServiceImpl implements PayloadService {

    private final PayloadRepository payloadRepository;
    private final PayloadMapper payloadMapper;
    private final StringGenerationService stringGenerationService;

    @Override
    public Mono<PayloadPostReferenceDTO> run(PayloadDTO payloadDTO) {
        final Payload payload = payloadMapper.toPayload(payloadDTO);
        if (payload.getMaxSize() < payload.getMinSize()) {
            throw new InvalidParameterException("Provided min and max size is not possible to proceed");
        }
        double possibilities = 0;
        for (int i = payload.getMinSize(); payload.getMaxSize() > i; i++) {
            possibilities = possibilities + Math.pow(payload.getCharacters().size(), i);
        }
        if (possibilities < payload.getAmountOfStrings()) {
            throw new IllegalArgumentException("It is not possible to proceed than many strings");
        }
        final Path randomFile = createRandomFile();
        payload.setFileName(randomFile.toString());
        return payloadRepository
                .save(payload)
                .flatMap(payload1 -> {
                            final List<String> currString = stringGenerationService.generatePossibleStrings(new StringGeneration(payload1));
                            currString.forEach(s -> writeToFile(randomFile, s));
                            return payloadRepository.save(payload1).map(payloadMapper::toPayloadPostReferenceDTO);
                        }
                );
    }

    @Override
    public Mono<Integer> countRunning() {
        return payloadRepository.countAllByStateIsNot(State.IDLE);
    }

    @Override
    public Mono<PayloadIdleResponseDTO> getPayloadContent(String id) {
        return payloadRepository
                .findById(id)
                .map(payloadMapper::toPayloadIdleResponseDTO);
    }

    private Path createRandomFile() {
        final String uniqueString = UUID.randomUUID().toString();
        final Path path = Paths.get(uniqueString);

        try {
            return Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(Path path, String stringToWrite) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write("\"" + stringToWrite + "\"," + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
