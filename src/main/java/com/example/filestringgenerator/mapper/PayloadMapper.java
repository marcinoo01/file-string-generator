package com.example.filestringgenerator.mapper;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.dto.PayloadDTO;
import com.example.filestringgenerator.dto.PayloadIdleResponseDTO;
import com.example.filestringgenerator.dto.PayloadPostReferenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PayloadMapper {
    @Mapping(source = "minSize", target = "minSize")
    @Mapping(source = "maxSize", target = "maxSize")
    @Mapping(source = "characters", target = "characters")
    @Mapping(source = "amountOfStrings", target = "amountOfStrings")
    Payload toPayload(PayloadDTO payloadDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "minSize", target = "minSize")
    @Mapping(source = "maxSize", target = "maxSize")
    @Mapping(source = "characters", target = "characters")
    @Mapping(source = "fileName", target = "fileName")
    PayloadIdleResponseDTO toPayloadIdleResponseDTO(Payload payload);

    @Mapping(source = "id", target = "id")
    PayloadPostReferenceDTO toPayloadPostReferenceDTO(Payload payload);
}
