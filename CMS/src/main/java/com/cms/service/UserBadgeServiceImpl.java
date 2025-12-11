package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Badge;
import com.cms.model.User;
import com.cms.model.Userbadge;
import com.cms.model.UserbadgeId;
import com.cms.repository.BadgeRepository;
import com.cms.repository.UserBadgeRepository;
import com.cms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserBadgeServiceImpl implements UserBadgeService {

    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public UserBadgeServiceImpl(UserBadgeRepository userBadgeRepository,
                                UserRepository userRepository,
                                BadgeRepository badgeRepository) {
        this.userBadgeRepository = userBadgeRepository;
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public List<Userbadge> getAll() {
        return userBadgeRepository.findAll();
    }

    @Override
    public Userbadge getById(UserbadgeId id) {
        return userBadgeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserBadge", "id", id));
    }

    @Override
    public Userbadge addBadgeToUser(Long userId, Long badgeId, Userbadge data) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Badge", "id", badgeId));

        UserbadgeId id = new UserbadgeId();
        id.setUserID(userId);
        id.setBadgeID(badgeId);

        Userbadge entity = new Userbadge();
        entity.setId(id);
        entity.setUserID(user);
        entity.setBadgeID(badge);
        entity.setDateEarned(data.getDateEarned());

        return userBadgeRepository.save(entity);
    }

    @Override
    public Userbadge updateUserBadge(UserbadgeId id, Userbadge data) {
        Userbadge existing = getById(id);

        existing.setDateEarned(data.getDateEarned());

        return userBadgeRepository.save(existing);
    }

    @Override
    public void removeBadgeFromUser(Long userId, Long badgeId) {

        UserbadgeId id = new UserbadgeId();
        id.setUserID(userId);
        id.setBadgeID(badgeId);

        Userbadge existing = getById(id);

        userBadgeRepository.delete(existing);
    }

    @Override
    public List<Userbadge> getBadgesForUser(Long userId) {
        return userBadgeRepository.findByUserID_Id(userId);
    }

    @Override
    public List<Userbadge> getBadgesForUserOrdered(Long userId) {
        return userBadgeRepository.findByUserID_IdOrderByDateEarnedDesc(userId);
    }

    @Override
    public List<Userbadge> getUsersForBadge(Long badgeId) {
        return userBadgeRepository.findByBadgeID_Id(badgeId);
    }
}
