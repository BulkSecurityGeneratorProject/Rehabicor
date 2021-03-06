package com.aditum.rehabicor.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A NonSpecificPainsSession.
 */
@Entity
@Table(name = "non_specific_pains_session")
public class NonSpecificPainsSession implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @Column(name = "non_specific_pains_session_relation")
    private Long nonSpecificPainsSessionRelation;

    @ManyToOne
    @JsonIgnoreProperties("nonSpecificPainsSessions")
    private Session session;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public NonSpecificPainsSession description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isExist() {
        return exist;
    }

    public NonSpecificPainsSession exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Long getNonSpecificPainsSessionRelation() {
        return nonSpecificPainsSessionRelation;
    }

    public NonSpecificPainsSession nonSpecificPainsSessionRelation(Long nonSpecificPainsSessionRelation) {
        this.nonSpecificPainsSessionRelation = nonSpecificPainsSessionRelation;
        return this;
    }

    public void setNonSpecificPainsSessionRelation(Long nonSpecificPainsSessionRelation) {
        this.nonSpecificPainsSessionRelation = nonSpecificPainsSessionRelation;
    }

    public Session getSession() {
        return session;
    }

    public NonSpecificPainsSession session(Session session) {
        this.session = session;
        return this;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NonSpecificPainsSession nonSpecificPainsSession = (NonSpecificPainsSession) o;
        if (nonSpecificPainsSession.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nonSpecificPainsSession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NonSpecificPainsSession{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", exist='" + isExist() + "'" +
            ", nonSpecificPainsSessionRelation=" + getNonSpecificPainsSessionRelation() +
            "}";
    }
}
