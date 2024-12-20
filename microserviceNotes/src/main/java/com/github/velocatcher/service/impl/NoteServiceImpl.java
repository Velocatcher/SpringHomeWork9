package com.github.velocatcher.service.impl;

import com.github.velocatcher.aspects.TrackUserAction;
import lombok.RequiredArgsConstructor;
import com.github.velocatcher.repository.NoteRepository;
import com.github.velocatcher.model.Note;
import com.github.velocatcher.service.NoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    // Создать заметку
    @TrackUserAction
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Получить все заметки
    @TrackUserAction
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Получить заметку по ID
    @TrackUserAction
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    // Обновить заметку
    @TrackUserAction
    @Override
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setTitle(note.getTitle());
        noteById.setContents(note.getContents());
        noteById.setCreationDate(LocalDateTime.now());

        return noteRepository.save(noteById);
    }

    // Удалить заметку
    @TrackUserAction
    @Override
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }

}
