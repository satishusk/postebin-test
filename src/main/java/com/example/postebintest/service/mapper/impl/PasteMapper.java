package com.example.postebintest.service.mapper;

import com.example.postebintest.data.Paste;
import com.example.postebintest.dto.PasteDto;

public class PasteMapper implements EntityMapper<PasteDto, Paste> {
  @Override
  public Paste mapDto(PasteDto dto) {
    Paste paste = new Paste();
    paste.setHashId(dto.getHashId());
    paste.setAccess(dto.getAccess());
    paste.setExpirationEndDateTime(dto.getExpirationEndDateExpirationTime());
    paste.setSeparatedText(dto.getSeparatedText());
    return paste;
  }

  @Override
  public PasteDto mapEntity(Paste paste) {
    PasteDto dto = new PasteDto();
    dto.setHashId(paste.getHashId());
    dto.setAccess(paste.getAccess());
    dto.setExpirationEndDateExpirationTime(paste.getExpirationEndDateTime());
    dto.setSeparatedText(paste.getSeparatedText());
    return dto;
  }
}