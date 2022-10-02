import axios from "axios";

export const client = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});

export const getAllCodeTypes = () => client.get("/api/code-types");

export const createCodeType = (data) => {
  client.post("/api/code-types", { name: data.codeTypeNm }, {});
};

export const deleteCodeType = async (id) => {
  await client.delete(`/api/code-types/${id}`);
};
