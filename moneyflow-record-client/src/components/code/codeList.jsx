import { Table, Button, Space, Modal } from "antd";
import { ExclamationCircleOutlined } from "@ant-design/icons";
import { useEffect, useState } from "react";
import { getAllCodes, getCode, deleteCode } from "../../api/api";

const { confirm } = Modal;

export default function CodeList({ setCodeForm, reRender }) {
  const [codes, setCodes] = useState([]);

  const initTable = async () => {
    const response = await getAllCodes();
    setCodes(response.data);
  };

  const { render } = reRender;
  useEffect(() => {
    initTable();
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
    //console.log("handleOnModify", id);
    const response = await getCode(id);
    //console.log(response.data);
    setCodeForm(response.data);
  };

  const handleOnDelete = (id) => {
    console.log("handleOnDelete", id);
    confirm({
      title: "코드를 삭제하시겠습니까?",
      icon: <ExclamationCircleOutlined />,
      //content: "코드를 삭제하시겠습니까?",
      onOk() {
        console.log("Ok");
        removeCode(id);
      },
      onCancel() {
        console.log("Cancel");
      },
    });
  };

  const handleOnAdd = () => {
    console.log("handleOnAdd");
    setCodeForm({ id: 0, name: "", codeTypeId: null });
  };

  const removeCode = async (id) => {
    await deleteCode(id);
    initTable();
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
