package org.java.project.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "name can't be null")
	private String titolo;
	@NotBlank(message = "descrizione can't be null")
	private String descrizione;
	@NotBlank(message = "url can't be null")
	private String url;
	@NotNull
	private boolean visibile;
	
	@ManyToMany
	private List<Categoria> categorias;
	
	public Photo() {}
	public Photo(String titolo, String descrizione, String url, boolean visibile, Categoria...categorias) {
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
		
		setCategorias(categorias);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisibile() {
		return visibile;
	}
	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public void setCategorias(Categoria[] categoria) {
		setCategorias(Arrays.asList(categoria));
	}
	public void addCategoria(Categoria categoria) {
		getCategorias().add(categoria);
	}
	public void removeCategoria(Categoria categoria) {
		getCategorias().remove(categoria);
	}
}
