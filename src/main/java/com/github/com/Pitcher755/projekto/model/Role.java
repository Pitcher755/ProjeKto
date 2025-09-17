package com.github.com.Pitcher755.projekto.model;

/**
 * Roles disponibles en la aplicación.
 * <p>
 * Usamos tres roles básicos:
 * <ul>
 * <li>ADMIN: control total (creación de proyectos globales, gestión de
 * usuarios)</li>
 * <li>MANAGER: puede crear/provisionar proyectos y asignar miembros</li>
 * <li>USER: colaborador estándar</li>
 * </ul>
 */
public enum Role {
    ADMIN,
    MANAGER,
    USER
}
