from flask import Flask, request, jsonify
import mysql.connector

app = Flask(__name__)

class Database:
    def __init__(self):
        self.host = "localhost"
        self.database = "ritimo"
        self.user = "root"  # Substitua com o usuário do banco de dados, se diferente
        self.password = "masterkey"
        self.connection = None
        self.cursor = None

    def connect(self):
        """Estabelece a conexão com o banco de dados."""
        try:
            self.connection = mysql.connector.connect(
                host=self.host,
                database=self.database,
                user=self.user,
                password=self.password
            )
            self.cursor = self.connection.cursor()
            print("Conexão com o banco de dados bem-sucedida")
        except mysql.connector.Error as err:
            print(f"Erro de conexão: {err}")

    def close(self):
        """Fecha a conexão com o banco de dados."""
        if self.cursor:
            self.cursor.close()
        if self.connection:
            self.connection.close()

    def insert_usuario(self, nome, email, data_aniversario, telefone, senha):
        """Insere um novo usuário na tabela usuario."""
        try:
            query = """
            INSERT INTO usuario (nome, email, data_aniversario, phone, senha)
            VALUES (%s, %s, %s, %s, %s)
            """
            self.cursor.execute(query, (nome, email, data_aniversario, telefone, senha))
            self.connection.commit()
            print("Usuário inserido com sucesso.")
            return self.cursor.lastrowid  # Retorna o ID do usuário inserido
        except mysql.connector.Error as err:
            print(f"Erro ao inserir usuário: {err}")
            self.connection.rollback()
            return None

# Routes
@app.route("/login", methods=["POST"])
def ping():
    return jsonify({
        "id": "1",
        "usuario":"Ricardo",
        "status":"true"
    })

@app.route("/cadastro", methods=["POST"])
def cadastro():

    dados = request.get_json()

    # Conecta ao banco e insere o usuário
    db = Database()
    db.connect()
    user_id = db.insert_usuario(
        dados.get("nome"),
        dados.get("email"),
        dados.get("dNascimento"),
        dados.get("telefone"),
        dados.get("senha")
    )
    db.close()

    if user_id:
        # Retorna o JSON de resposta com o ID do usuário criado
        return jsonify({
            "id": user_id,
            "usuario": dados.get("nome"),
            "status": "true"
        })
    else:
        return jsonify({
            "status": "false",
            "message": "Erro ao cadastrar usuário"
        }), 500

@app.route("/", methods=["GET"])
def main():
    return jsonify({
        "id": "1",
        "usuario":"Ricardo",
        "status":"true"
    })

# Start the Server
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=3011, debug=True)