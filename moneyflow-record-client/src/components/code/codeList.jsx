import { Table, Button, Space } from "antd";
import { useEffect, useState } from "react";
import { getAllCodes, getCode } from "../../api/api";

export default function CodeList({ code, onAdd, callback, reRender }) {
  const [codes, setCodes] = useState([]);

  const getCodes = async () => {
    const response = await getAllCodes();
    setCodes(response.data);
  };

  const { render } = reRender;
  useEffect(() => {
    getCodes();
  }, [render]);

  const columns = [
    {
      title: "Id",
      dataIndex: "id",
      key: "id",
      width: "10%",
      align: "center",
    },
    {
      title: "Code Name",
      dataIndex: "name",
      key: "name",
      width: "40%",
    },
    {
      title: "Code Type",
      dataIndex: "codeTypeName",
      key: "codeTypeName",
      width: "20%",
    },
    {
      title: "Action",
      key: "action",
      align: "center",
      render: (_, record) => (
        <Space size="middle">
          <Button onClick={() => handleOnModify(record.id)}>Modify</Button>
          <Button onClick={() => handleOnDelete(record.id)}>Delete</Button>
        </Space>
      ),
    },
  ];

  const handleOnModify = async (id) => {
    console.log("handleOnModify", id);
    const response = await getCode(id);
    console.log(response.data);
    callback(response.data);
  };

  const handleOnDelete = (id) => {
    console.log("handleOnDelete", id);
  };

  const handleOnAdd = () => {
    console.log("handleOnAdd");
    callback();
  };

  return (
    <div>
      <Button
        type="primary"
        style={{ float: "right", marginBottom: 16 }}
        onClick={handleOnAdd}
      >
        Add
      </Button>
      <Table columns={columns} dataSource={codes} rowKey="id"></Table>
    </div>
  );
}
