import { Table, Button } from "antd";

export default function CodeList() {
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
      dataIndex: "codeType",
      key: "codeType",
      width: "20%",
    },
    {
      title: "Action",
      key: "action",
      align: "center",
      render: (_, record) => (
        <Space size="middle">
          <Button onClick={() => onModify(record.id)}>Modify</Button>
          <Button onClick={() => onRemove(record.id)}>Delete</Button>
        </Space>
      ),
    },
  ];

  const handleOnAdd = () => {
    console.log("handleOnAdd");
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
      <Table columns={columns}></Table>
    </div>
  );
}
