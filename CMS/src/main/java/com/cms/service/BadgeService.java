package com.cms.service;

import com.cms.model.Badge;

import java.util.List;

public interface BadgeService {

    List<Badge> getAllBadges();

    Badge getBadgeById(Long id);

    Badge getBadgeByName(String name);

    Badge createBadge(Badge badge);

    Badge updateBadge(Long id, Badge badgeDetails);

    String deleteBadge(Long id);
}
