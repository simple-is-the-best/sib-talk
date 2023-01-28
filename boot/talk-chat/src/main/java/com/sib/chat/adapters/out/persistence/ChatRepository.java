package com.sib.chat.adapters.out.persistence;

import com.sib.chat.domain.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, Long> {

    Optional<Chat> findChatByChannel_Id(Long channelId);
    Page<Chat> findAllByUserId(Long userId);
}
