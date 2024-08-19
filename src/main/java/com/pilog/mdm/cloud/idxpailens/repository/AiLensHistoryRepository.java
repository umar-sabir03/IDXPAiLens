package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.AiLensHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AiLensHistoryRepository extends JpaRepository<AiLensHistory,String> {
    @Query("SELECT a.promptInput FROM AiLensHistory a WHERE LOWER(a.createBy) = LOWER(:username) ORDER BY a.createBy ASC")
    List<String> findRecentPromptsByUsername(@Param("username") String username);
}
