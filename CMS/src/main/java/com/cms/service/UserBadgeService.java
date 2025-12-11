package com.cms.service;

import com.cms.model.Userbadge;
import com.cms.model.UserbadgeId;

import java.util.List;

public interface UserBadgeService {

    List<Userbadge> getAll();

    Userbadge getById(UserbadgeId id);

    Userbadge addBadgeToUser(Long userId, Long badgeId, Userbadge userbadgeData);

    Userbadge updateUserBadge(UserbadgeId id, Userbadge userbadgeData);

    void removeBadgeFromUser(Long userId, Long badgeId);

    List<Userbadge> getBadgesForUser(Long userId);

    List<Userbadge> getBadgesForUserOrdered(Long userId);

    List<Userbadge> getUsersForBadge(Long badgeId);
}
