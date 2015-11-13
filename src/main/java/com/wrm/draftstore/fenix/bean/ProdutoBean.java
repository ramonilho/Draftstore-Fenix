/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.service.jpaimpl.ProdutoServiceJPAImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

  /**
   * Creates a new instance of ProdutoBean
   */
  public ProdutoBean() {
  }
  
  // Produtos Fake
//  private List<Produto> ofertasSemana = new ProdutoServiceFakeImpl().listar(0, 10);
  
  // Produtos JPA
  private List<Produto> ofertasSemana = new ProdutoServiceJPAImpl().listar(0, 10);
  
  private Produto produtoDetalhe;
  
  public String carregarDetalhes(){
    this.produtoDetalhe = this.getProduto();
    System.out.println("PRODUTO DETALHE: "+this.produtoDetalhe.getModelo());
    
    return "detalhe?faces-redirect=true";
  }

  public List<Produto> getOfertasSemana() {
    return ofertasSemana;
  }
  
  public Produto getProduto() {
    FacesContext contexto = FacesContext.getCurrentInstance();
    return ofertasSemana.get(getId(contexto)-1);
  }
    
  private int getId(FacesContext fc) {
    Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
    return Integer.parseInt(params.get("id"));
  }
  
  public Produto getProdutoDetalhe() {
    return produtoDetalhe;
  }

  public void setProdutoDetalhe(Produto produtoDetalhe) {
    this.produtoDetalhe = produtoDetalhe;
  }
  
}