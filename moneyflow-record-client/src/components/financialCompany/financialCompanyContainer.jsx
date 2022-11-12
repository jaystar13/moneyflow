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

  const [financialCompany, setFinancialCompany] = useState({ id: 0 });

  const callbackModfy = (modifyData) => {
    setFinancialCompany(modifyData);
  };

  const [render, setRender] = useState(false);

  return (
    <div>
      <Title>금융기관</Title>
      <FinancialCompanyForm
        configure={modalConfigure}
        data={financialCompany}
        reRender={{ render: render, setRender: setRender }}
      />
      <FinancialCompanyList
        callbackModfy={callbackModfy}
        reRender={{ render: render, setRender: setRender }}
      />
    </div>
  );
}
