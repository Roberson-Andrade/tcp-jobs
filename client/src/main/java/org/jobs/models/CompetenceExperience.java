package org.jobs.models;

public class CompetenceExperience {
    String competence;
    Integer experience;

    public CompetenceExperience(String competence, Integer experience) {
        this.competence = competence;
        this.experience = experience;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
