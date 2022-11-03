import { useState } from "react";
import FinancialCompanyForm from "./financialCompanyForm";
import FinancialCompanyList from "./financialCompanyList";
import { Typography } from "antd";

const { Title } = Typography;

export default function FinancialCompanyContainer() {
  const [modalConfigure, setModalConfigure] = useState({
    useModal: false,
    modalOpen: false,
  });

  const defaultFinancialCompany = {
    id: 0,
    namd: "",
  };

  return (
    <div>
      <Title>금융기관</Title>
      <FinancialCompanyForm
        configure={modalConfigure}
        data={defaultFinancialCompany}
      />
      <FinancialCompanyList />
    </div>
  );
}
