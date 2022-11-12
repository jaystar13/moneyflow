import { Table, Space, Button } from "antd";
import { useEffect } from "react";
import { useState } from "react";
import { getAllFinancialCompaies, getFinancialCompany } from "../../api/api";

export default function FinancialCompanyList({
  callbackModfy: toFinancialCompanyForm,
  reRender,
}) {
  const columns = [
    {
      title: "Id",
      dataIndex: "id",
      key: "id",
      width: "5%",
      align: "center",
    },
    {
      title: "명칭",
      dataIndex: "name",
      key: "name",
      width: "20%",
      align: "center",
    },
    {
      title: "금융기관구분",
      dataIndex: "companyTypeTitle",
      key: "companyTypeTitle",
      width: "10%",
      align: "center",
    },
    {
      title: "사용여부",
      dataIndex: "isUsable",
      key: "isUsable",
      width: "8%",
      align: "center",
      render: (_, record) => (record.usable ? "사용" : "미사용"),
    },
    {
      title: "참고사항",
      dataIndex: "definition",
      key: "definition",
      width: "30%",
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

  const [dataSource, setDataSource] = useState([]);

  const initTable = async () => {
    const { data: financialCompanies } = await getAllFinancialCompaies();
    setDataSource(financialCompanies);
  };

  const { render } = reRender;
  useEffect(() => {
    initTable();
  }, [render]);

  const onModify = async (financialCompanyId) => {
    const { data } = await getFinancialCompany(financialCompanyId);
    toFinancialCompanyForm(data);
  };

  const onAdd = () => {};

  return (
    <div>
      <Button
        type="primary"
        style={{ float: "right", marginBottom: 16 }}
        onClick={onAdd}
      >
        Add
      </Button>
      <Table columns={columns} dataSource={dataSource} rowKey="id" />
    </div>
  );
}
