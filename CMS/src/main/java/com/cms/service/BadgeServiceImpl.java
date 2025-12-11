package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Badge;
import com.cms.repository.BadgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Override
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    @Override
    public Badge getBadgeById(Long id) {
        return badgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Badge", "id", id));
    }

    @Override
    public Badge getBadgeByName(String name) {
        return badgeRepository.findBadgeByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Badge", "name", name));
    }

    @Override
    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    @Override
    public Badge updateBadge(Long id, Badge badgeDetails) {
        Badge existing = getBadgeById(id);

        existing.setName(badgeDetails.getName());
        existing.setCriteria(badgeDetails.getCriteria());
        existing.setImageUrl(badgeDetails.getImageUrl());

        return badgeRepository.save(existing);
    }

    @Override
    public String deleteBadge(Long id) {
        Badge badge = getBadgeById(id);
        badgeRepository.delete(badge);
        return "Badge with ID " + id + " deleted successfully.";
    }
}
