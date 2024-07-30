package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.DalAiLensQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DalAiLensQuestionsRepository extends JpaRepository<DalAiLensQuestions,String> {

    @Query("Select question from DalAiLensQuestions where source=:source and domain=:domain")
   String getPromptQuestionBasedOnTypedValue(String source,String domain);
}
