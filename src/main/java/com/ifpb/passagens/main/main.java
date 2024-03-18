
package com.ifpb.passagens.main;

import java.util.List;

import com.ifpb.passagens.dao.PassageiroDAO;
import com.ifpb.passagens.dao.ItinerarioDAO;
import com.ifpb.passagens.dao.OnibusDAO;
import com.ifpb.passagens.entities.Passageiro;
import com.ifpb.passagens.entities.Itinerario;
import com.ifpb.passagens.entities.Onibus;

public class main {

    public static void main(String[] args) throws ClassNotFoundException {
        // Teste da conexão com o banco de dados
        PassageiroDAO passageiroDao = new PassageiroDAO();
        ItinerarioDAO itinerarioDao = new ItinerarioDAO();
        OnibusDAO onibusDao = new OnibusDAO();

        // Teste de inserção de um passageiro
        Passageiro passageiro = new Passageiro();
        passageiro.setNome("João Silva");
        passageiro.setCpf("123.456.789-00");
        passageiroDao.inserirPassageiro(passageiro);

        // Teste de busca por ID de um passageiro
        Passageiro passageiroBuscado = passageiroDao.buscarPorId(1L);
        System.out.println("Passageiro encontrado:");
        System.out.println("Nome: " + passageiroBuscado.getNome());
        System.out.println("CPF: " + passageiroBuscado.getCpf());

        // Teste de listagem de todos os passageiros
        List<Passageiro> passageiros = passageiroDao.listarTodos();
        System.out.println("Todos os passageiros:");
        for (Passageiro p : passageiros) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("CPF: " + p.getCpf());
        }

        // Teste de atualização de um passageiro
        passageiroBuscado.setNome("Maria Oliveira");
        passageiroDao.atualizarPassageiro(passageiroBuscado);

        // Teste de exclusão de um passageiro
        passageiroDao.excluirPassageiro(1L);

        // Teste de inserção de um itinerário
        Itinerario itinerario = new Itinerario();
        itinerario.setOrigem("São Paulo");
        itinerario.setDestino("Rio de Janeiro");
        itinerarioDao.inserirItinerario(itinerario);

        // Teste de inserção de um ônibus
        Onibus onibus = new Onibus();
        onibus.setPlaca("ABC-1234");
        onibus.setCapacidade(50);
        onibusDao.inserirOnibus(onibus);
    }
}
