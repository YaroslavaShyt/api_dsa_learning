package com.api.api.repositories.rewards;


import com.api.api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RewardsRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.bytes = :bytes WHERE u.id = :userId")
    void updateBytes(Long userId, int bytes);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.fans = :fans WHERE u.id = :userId")
    void updateFans(Long userId, int fans);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.hash = :hash WHERE u.id = :userId")
    void updateHash(Long userId, int hash);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.bytes = :bytes, u.fans = :fans, u.hash = :hash WHERE u.id = :userId")
    void updateRewards(Long userId, int bytes, int fans, int hash);
}

