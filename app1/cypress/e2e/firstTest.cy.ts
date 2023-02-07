/// <reference types="cypress" />

// Welcome to Cypress!
//
// This spec file contains a variety of sample tests
// for a todo list app that are designed to demonstrate
// the power of writing tests in Cypress.
//
// To learn more about how Cypress works and
// what makes it such an awesome testing tool,
// please read our getting started guide:
// https://on.cypress.io/introduction-to-cypress

describe('example localhost', () => {
    beforeEach(() => {
      cy.visit('http://localhost:9000/')
      cy.contains('Cuenta').click()

    })

    it('usuario admin crea un nuevo usuario', () => {
      cy.contains('Iniciar sesión').click()
      cy.get('#username').type('admin')
      cy.get('#password').type('admin').type('{enter}')
      cy.contains('¡Bienvenido, Java Hipster!')
      cy.contains('Administración').click()
      cy.contains('Gestión de usuarios').click()
      cy.contains('Crear un nuevo usuario').click()

      cy.get('#login').type('jlocamuz', {force: true})
      cy.get('#firstName').type('Julia',{force: true})
      cy.get('#lastName').type('Locamuz', {force: true})
      cy.get('#email').type('jlocamuz@gmail.com', {force: true})
      cy.get('[name="langKey"]').select('Español')
      cy.get('[name="authorities"]').select('ROLE_USER').type('{enter}')
      cy.get("a[href='/admin/user-management/jlocamuz/delete']").click()
      cy.get('*[class="btn btn-danger"]').click()

})
  it('user accede a la app', ()=> {
    cy.contains('Iniciar sesión').click()
    cy.get('#username').type('user')
    cy.get('#password').type('user').type('{enter}')
    cy.contains('¡Bienvenido, Java Hipster!')
  })
  });

