package estrutura.ufpb.br;

import java.util.ArrayList;
import java.util.List;

public class GrafoComListaDeAdjacencias implements Grafo {

	private List<Vertice> vertices = new ArrayList<>();
	private List<VerticeInterno> verticesInternos = new ArrayList<>();

	public GrafoComListaDeAdjacencias(String... nomesVertices) {
		for (String nomeVertice : nomesVertices) {
			this.addVertice(nomeVertice);
		}
	}

	@Override
	public Vertice addVertice(String nome) {
		Vertice vertice = new Vertice();
		vertice.setNome(nome);
		vertices.add(vertice);

		VerticeInterno verticeInterno = new VerticeInterno();
		verticeInterno.setVertice(vertice);
		verticesInternos.add(verticeInterno);

		return vertice;
	}

	public Vertice getVertice(String nome) {
		for (Vertice vertice : vertices) {
			if (vertice.getNome().equals(nome)) {
				return vertice;
			}
		}

		return null;
	}

	private VerticeInterno getVerticeInterno(Vertice v) {
		for (VerticeInterno verticeInterno : verticesInternos) {
			if (verticeInterno.getVertice().equals(v)) {
				return verticeInterno;
			}
		}

		throw new RuntimeException("Vertice interno não existe");
	}

	@Override
	public void addAresta(Vertice v1, Vertice v2) {

		Aresta arestas = new Aresta();

		arestas.setOrigem(v1); // Origem do vertice

		arestas.setDestino(v2); // Destino do vertice

		VerticeInterno verticeInternoV1 = getVerticeInterno(v1);

		verticeInternoV1.getArestas().add(arestas);

	}

	@Override
	public List<Vertice> getVertices() {
		return vertices;
	}

	@Override
	public boolean segue(Vertice v1, Vertice v2) {
		for (VerticeInterno verificarVertices : verticesInternos) { // Verifica se existe o V1
			if (verificarVertices.getVertice().equals(v1)) {

				for (Aresta aresta : verificarVertices.getArestas()) {

					if (aresta.getDestino().equals(v2)) { // O vertice V1 segue o vertice V2

						return true; // retorna true se segue
					}
				}
			}
		}
		return false; // retorna false se não segue
	}
}

class VerticeInterno {

	private Vertice vertice;
	private List<Aresta> arestas = new ArrayList<>();

	public Vertice getVertice() {
		return vertice;
	}

	public void setVertice(Vertice vertice) {
		this.vertice = vertice;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

}