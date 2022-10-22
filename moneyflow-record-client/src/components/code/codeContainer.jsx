import { Typography } from "antd";
import CodeForm from "./codeForm";
import CodeList from "./codeList";
import { useState } from "react";

const { Title } = Typography;

export default function CodeContainer() {
  const [modalConfigure, setModalConfigure] = useState({
    useModal: true,
    modalOpen: false,
  });

  const [render, setRender] = useState(false);

  const onAdd = () => {
    setModalConfigure({ ...modalConfigure, modalOpen: true });
  };

  return (
    <div>
      <Title>Code {render ? "true" : "false"}</Title>
      <CodeForm
        configure={modalConfigure}
        code={{ id: 0, name: "", codeTypeId: null }}
        reRender={{ render: render, setRender: setRender }}
      />
      <CodeList
        onAdd={onAdd}
        reRender={{ render: render, setRender: setRender }}
      />
    </div>
  );
}
