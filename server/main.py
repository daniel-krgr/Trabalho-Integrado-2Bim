from flask import Flask, jsonify

app = Flask(__name__)

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
    return jsonify({
        "id": "1",
        "usuario":"Ricardo",
        "status":"true"
    })



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