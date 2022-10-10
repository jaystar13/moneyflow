import React from "react";
import { Input, Button, Space, Table, Typography, Modal } from "antd";

const { Search } = Input;
const { Title } = Typography;

function CodeTypeList({ codeTypes, onRemove, onModify, onSearch, onAdd }) {
  const handleOnChange = (e) => {
    onSearch(e.currentTarget.value);
  };

  const columns = [
    {
      title: "Id",
      dataIndex: "id",
      key: "id",
      width: "10%",
      align: "center",
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      width: "60%",
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
    onAdd();
  };

  return (
    <>
      <Title>Code Type</Title>
      <Search
        type="text"
        name="searchName"
        placeholder="Search Code Name"
        onChange={handleOnChange}
        style={{ width: 200 }}
      />
      <Button type="primary" style={{ float: "right" }} onClick={handleOnAdd}>
        Add
      </Button>
      <Table columns={columns} dataSource={codeTypes} rowKey="id" />
    </>
  );
}

export default CodeTypeList;
