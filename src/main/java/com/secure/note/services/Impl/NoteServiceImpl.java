package com.secure.note.services.Impl;

import com.secure.note.models.Note;
import com.secure.note.repositories.NoteRepository;
import com.secure.note.services.AuditLogService;
import com.secure.note.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    private AuditLogService auditLogService;
    
    //새 노트 생성
    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        auditLogService.logNoteCreation(username, note);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        note.setContent(content); //내용 업데이트
        // note 를 DB 에서 가져왔음 id 있음 save 시 업데이트 됨
        Note updatedNote = noteRepository.save(note);
        auditLogService.logNoteUpdate(username, note); //업데이트 로그
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(()-> new RuntimeException("Note not found"));
        noteRepository.delete(note);
        auditLogService.logNoteDeletion(username, noteId); //삭제 로그
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository.findByOwnerUsername(username);
        return personalNotes;
    }
}
