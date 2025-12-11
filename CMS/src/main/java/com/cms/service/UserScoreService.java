package com.cms.service;

import com.cms.model.Userscore;
import com.cms.model.UserscoreId;

import java.util.List;

public interface UserScoreService {

    List<Userscore> getAllScores();

    Userscore getScoreById(UserscoreId id);

    Userscore createOrUpdateScore(Userscore userscore);

    void deleteScore(UserscoreId id);

    List<Userscore> getScoresByUser(Long userId);

    List<Userscore> getScoresByBadge(Long badgeId);

    Userscore getScoreByUserAndBadge(Long userId, Long badgeId);
}
