package com.secure.note.services.Impl;

import com.secure.note.models.AuditLog;
import com.secure.note.models.Note;
import com.secure.note.repositories.AuditLogRepository;
import com.secure.note.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;


    //노트의 로그 생성하기
    @Override
    public void logNoteCreation(String username, Note note){
        AuditLog log = new AuditLog();
        log.setAction("CREATE");
        log.setUsername(username);//글쓴이
        log.setNoteId(note.getId());
        log.setNoteContent(note.getContent()); //새로 작성한 내용
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);

    }

    //노트 업데이트 로그
    @Override
    public void logNoteUpdate(String username, Note note){
        AuditLog log = new AuditLog();
        log.setAction("UPDATE");
        log.setUsername(username);
        log.setNoteId(note.getId());
        log.setNoteContent(note.getContent()); //수정된 내용
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    //노트 삭제 로그
    @Override
    public void logNoteDeletion(String username, Long noteId){
        AuditLog log = new AuditLog();
        log.setAction("DELETE");
        log.setUsername(username);
        log.setNoteId(noteId);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAuditLogsForNoteId(Long id) {
        return auditLogRepository.findByNoteId(id);
    }
}
