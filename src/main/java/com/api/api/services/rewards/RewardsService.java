package com.api.api.services.rewards;


import com.api.api.repositories.rewards.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

    @Autowired
    private  RewardsRepository rewardsRepository;

    public void updateUserBytes(Long userId, int bytes) {
        rewardsRepository.updateBytes(userId, bytes);
    }

    public void updateUserFans(Long userId, int fans) {
        rewardsRepository.updateFans(userId, fans);
    }

    public void updateUserHash(Long userId, int hash) {
        rewardsRepository.updateHash(userId, hash);
    }

    public void updateAllRewards(Long userId, int bytes, int fans, int hash) {
        rewardsRepository.updateRewards(userId, bytes, fans, hash);
    }
}

