package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Userscore;
import com.cms.model.UserscoreId;
import com.cms.repository.UserScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserScoreServiceImpl implements UserScoreService {

    private final UserScoreRepository userScoreRepository;

    public UserScoreServiceImpl(UserScoreRepository userScoreRepository) {
        this.userScoreRepository = userScoreRepository;
    }

    @Override
    public List<Userscore> getAllScores() {
        return userScoreRepository.findAll();
    }

    @Override
    public Userscore getScoreById(UserscoreId id) {
        return userScoreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Userscore", "id", id));
    }

    @Override
    public Userscore createOrUpdateScore(Userscore userscore) {
        return userScoreRepository.save(userscore);
    }

    @Override
    public void deleteScore(UserscoreId id) {
        Userscore existing = getScoreById(id);
        userScoreRepository.delete(existing);
    }

    @Override
    public List<Userscore> getScoresByUser(Long userId) {
        return userScoreRepository.findByUserID_Id(userId);
    }

    @Override
    public List<Userscore> getScoresByBadge(Long badgeId) {
        return userScoreRepository.findByBadgeID_Id(badgeId);
    }

    @Override
    public Userscore getScoreByUserAndBadge(Long userId, Long badgeId) {
        return userScoreRepository.findByUserID_IdAndBadgeID_Id(userId, badgeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Userscore",
                                "userId + badgeId",
                                userId + " + " + badgeId));
    }
}
