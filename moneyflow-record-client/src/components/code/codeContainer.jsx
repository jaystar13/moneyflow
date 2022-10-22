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

  const [code, setCode] = useState({ id: 0, name: "", codeTypeId: null });

  const callback = (code) => {
    console.log(code);
    if (code) {
      setCode(code);
    }

    console.log("code", this.useState.code);
    setModalConfigure({ ...modalConfigure, modalOpen: true });
  };

  return (
    <div>
      <Title>Code</Title>
      <CodeForm
        configure={modalConfigure}
        code={code}
        reRender={{ render: render, setRender: setRender }}
      />
      <CodeList
        code={code}
        onAdd={onAdd}
        callback={callback}
        reRender={{ render: render, setRender: setRender }}
      />
    </div>
  );
}
