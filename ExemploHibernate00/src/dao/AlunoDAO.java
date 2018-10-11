package dao;

import bean.Aluno;
import database.Conexao;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Logan Michel
 */
public class AlunoDAO {

    public List<Aluno> obterTodos() {
        List<Aluno> alunos = new ArrayList<>();
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            alunos = conexao.session.createQuery("from Aluno").list();
        }
        return alunos;
    }

    public Aluno obterPeloId(int id) {
        Aluno aluno = null;
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            aluno = conexao.session.get(Aluno.class, id);
            conexao.transaction.commit();
            conexao.session.close();
            return aluno;
        }
        return aluno;
    }

    public int inserir(Aluno aluno) {
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            conexao.session.save(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return aluno.getId();
        }
        return -1;
    }

    public boolean alterar(Aluno aluno) {
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            conexao.session.update(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return true;
        }
        
        return false;
    }

    public boolean excluir(int id) {
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            Aluno aluno = conexao.session.get(Aluno.class, id);
            conexao.session.delete(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return true;
        }
        return false;
    }

}
