package com.api.api.services.rewards;


import com.api.api.repositories.rewards.RewardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RewardsService {

    private  final RewardsRepository rewardsRepository;

    public void updateAllRewards(Long userId, int bytes, int fans, int hash) {
        rewardsRepository.updateRewards(userId, bytes, fans, hash);
    }
}

