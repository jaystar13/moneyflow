import { ExclamationCircleOutlined } from "@ant-design/icons";
import { Table, Space, Button, Modal } from "antd";
import { useEffect } from "react";
import { useState } from "react";
import {
  getAllFinancialCompaies,
  getFinancialCompany,
  deleteFinancialCompany,
} from "../../api/api";

const { confirm } = Modal;

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
          <Button onClick={() => onDelete(record.id)}>Delete</Button>
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

  const onDelete = (financialCompanyId) => {
    confirm({
      title: "삭제하시겠습니까?",
      icon: <ExclamationCircleOutlined />,
      content: "삭제를 하시면 당연하게도 지워집니다!😱",
      async onOk() {
        await deleteFinancialCompany(financialCompanyId);
        initTable();
      },
    });
  };

  return (
    <div>
      <Table columns={columns} dataSource={dataSource} rowKey="id" />
    </div>
  );
}
