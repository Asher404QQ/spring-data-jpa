package ru.kors.model;

public interface AuthorProjection {
    Long getId();
    String getAlias();
    String getFirstName();
    String getLastName();
    String getEmail();
    RoleProjection getRole();
}
