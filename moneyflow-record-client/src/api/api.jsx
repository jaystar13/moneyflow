import axios from "axios";

export const client = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});

export const getAllCodeTypes = () => client.get("/api/code-types");

export const getCodeType = (id) => client.get(`/api/code-types/${id}`);

export const searchCodeTypes = (searchName) =>
  client.get(`/api/code-types/contain-word`, {
    params: { contain: searchName },
  });

export const createCodeType = async (data) => {
  await client.post("/api/code-types", data, {}).catch(function (error) {
    if (error.response) {
      console.log(
        error.response.data.errors.forEach((element) => {
          console.log(element.defaultMessage);
        })
      );
    }
  });
};

export const updateCodeType = async (id, data) => {
  await client.put(`api/code-types/${id}`, data);
};

export const deleteCodeType = async (id) => {
  await client.delete(`/api/code-types/${id}`);
};

export const createCode = async (data) => {
  await client.post("/api/codes", data, {}).catch(function (error) {
    if (error.response) {
      console.log(
        error.response.data.errors.forEach((element) => {
          console.log(element.defaultMessage);
        })
      );
    }
  });
};
