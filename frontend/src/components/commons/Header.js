import { Link } from 'react-router-dom';

function Header() {
  return (
    <header>
      <nav>
        <Link to="/">Inicio</Link>
        <Link to="/personalizar">Personalizar</Link>
        {/* Otros enlaces si es necesario */}
      </nav>
    </header>
  );
}

export default Header;
