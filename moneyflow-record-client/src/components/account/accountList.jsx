import { Table, Space, Button } from "antd";
import { useEffect } from "react";
import { useState } from "react";
import { getAllAccounts } from "../../api/api";

export default function AccountList() {
  const columns = [
    {
      title: "Id",
      dataIndex: "id",
      key: "id",
      width: "5%",
      align: "center",
    },
    {
      title: "계좌명칭",
      dataIndex: "name",
      key: "name",
      width: "20%",
      align: "center",
    },
    {
      title: "금융기관",
      dataIndex: "financialCompany",
      key: "financialCompany",
      width: "10%",
      align: "center",
      render: (financialCompany) => {
        return financialCompany.name;
      },
    },
    {
      title: "계좌번호",
      dataIndex: "accountNo",
      key: "accountNo",
      width: "15%",
      align: "center",
    },
    {
      title: "시작일자",
      dataIndex: "fromDate",
      key: "fromDate",
      width: "8%",
      align: "center",
    },
    {
      title: "종료일자",
      dataIndex: "toDate",
      key: "toDate",
      width: "8%",
      align: "center",
    },
    {
      title: "설명",
      dataIndex: "definition",
      key: "definition",
      width: "*%",
    },
    {
      title: "Action",
      key: "action",
      align: "center",
      render: (_, record) => (
        <Space size="middle">
          <Button onClick={() => onModify(record.id)}>Modify</Button>
          <Button onClick={() => onDelete(record.id)}>Delete</Button>
        </Space>
      ),
    },
  ];

  const [dataSource, setDataSource] = useState([]);

  const initTable = async () => {
    const { data } = await getAllAccounts();
    console.log("data", data);
    setDataSource(data);
  };

  useEffect(() => {
    initTable();
  }, []);
  return (
    <div>
      <Table columns={columns} dataSource={dataSource} rowKey="id" />
    </div>
  );
}
