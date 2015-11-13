/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.UsuarioService;
import com.wrm.draftstore.common.service.fakeimpl.UsuarioServiceFakeImpl;
import com.wrm.draftstore.common.service.jpaimpl.UsuarioServiceJPAImpl;
import com.wrm.draftstore.fenix.entity.UsuarioSistema;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
  // Construtor padrao
  public UsuarioBean() {
  }
  
  // Usuarios Fake
//  private UsuarioService usuarioService = new UsuarioServiceFakeImpl();
  
  // Usuarios DB
  private UsuarioService usuarioService = new UsuarioServiceJPAImpl();
  
  //<editor-fold defaultstate="collapsed" desc="Atributos">
  private String nome;
  private String email;
  private String senha;
  
  private Usuario usuarioLogado;
  private UsuarioSistema usuarioSistema;
  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Metodos">
  public String efetuarLogin(){
    UsuarioSistema usuarioSis = autenticar(email, senha);
    if (usuarioSis != null) {
      this.usuarioSistema = usuarioSis;
      System.out.println("[WRMLOG] Login efetuado com sucesso" 
              + "\n> "+usuarioLogado.getNome()+" logado.");
      return "/perfil.xhtml?faces-redirect=true";
    }
    return "/home.xhtml?faces-redirect=true";
  }
  
  public String efetuarLogout(){
    usuarioLogado = null;
    return "/home.xhtml?faces-redirect=true";
  }
  
  public UsuarioSistema autenticar(String email, String senha){
    Usuario usuario = usuarioService.realizarLogin(email, senha);
    if (usuario != null) {
      UsuarioSistema userSis = new UsuarioSistema(email, senha, new String[]{"ADMIN"});
      this.usuarioLogado = usuario;
      return userSis;
    }
    return null;
  }
  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Getters e setters">
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getSenha() {
    return senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public Usuario getUsuarioLogado() {
    return usuarioLogado;
  }

  public void setUsuarioLogado(Usuario usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
  }

  public UsuarioSistema getUsuarioSistema() {
    return usuarioSistema;
  }

  public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
    this.usuarioSistema = usuarioSistema;
  }
  //</editor-fold>
}