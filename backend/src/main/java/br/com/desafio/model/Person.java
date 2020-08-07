/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.model;

/**
 *
 * @author renatomori
 */
public class Person {
//    Nome - obrigatório

    private String name;
//Sexo
//E-mail - não obrigatório, deve ser validado caso preenchido
//Data de Nascimento - obrigatório, deve ser validada
//Naturalidade
//Nacionalidade
//CPF - obrigatório, deve ser validado (formato e não pode haver dois cadastros com mesmo cpf)
//Obs: a data de cadastro e atualização dos dados devem ser armazenados.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
