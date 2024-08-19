package com.pilog.mdm.cloud.idxpailens.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Data
@NoArgsConstructor
public class StgTerminologyDTO {
        private String definition;
        private String abbreviation;
        private String guidelines;
        private String serviceCategory;
        private String unspscCode;
        private String unspscDesc;
        @JsonIgnore
        @Setter(AccessLevel.NONE)
        private Blob content;
        @JsonProperty("content")
        private String base64Content;
        private String recordGroup;

        public void setBase64Content(Blob content) throws SQLException, IOException {
                this.content = content;
                if (content != null) {
                        try (InputStream inputStream = content.getBinaryStream();
                             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = inputStream.read(buffer)) != -1) {
                                        outputStream.write(buffer, 0, bytesRead);
                                }
                                this.base64Content = Base64.getEncoder().encodeToString(outputStream.toByteArray());
                        }
                }
        }

        public StgTerminologyDTO(String definition, String abbreviation, String guidelines, String serviceCategory, String unspscCode, String unspscDesc, Blob content, String recordGroup) {
                this.definition = definition;
                this.abbreviation = abbreviation;
                this.guidelines = guidelines;
                this.serviceCategory = serviceCategory;
                this.unspscCode = unspscCode;
                this.unspscDesc = unspscDesc;
                this.content = content;
                this.recordGroup = recordGroup;
        }
}