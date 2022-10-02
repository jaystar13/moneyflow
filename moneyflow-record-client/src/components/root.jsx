import { Outlet, Link, Form } from "react-router-dom";

export default function Root() {
  return (
    <>
      <div id="sidebar">
        <h1>Money Flow Record</h1>
        <div>
          <Form id="search-form" role="search">
            <input
              type="search"
              id="q"
              aria-label="Search Menus"
              placeholder="Search"
              name="q"
            />
            <div id="search-spinner" aria-hidden hidden={true}></div>
            <div className="sr-only" aria-live="polite"></div>
          </Form>
        </div>
        <nav>
          <ul>
            <li>
              <Link to={`codeType`}>코드타입</Link>
            </li>
            <li>
              <Link to={`code`}>코드</Link>
            </li>
          </ul>
        </nav>
      </div>
      <div id="detail">
        <Outlet />
      </div>
    </>
  );
}
