package io.crowdcode.cloudbay.auction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Slf4j
@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Product extends AbstractEntity<Long> {

    private String title;

    @Lob
    private String description;

    @Column(unique = true)
    private String productUuid;

}
