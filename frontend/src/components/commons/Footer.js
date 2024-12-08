import React from "react";
import "./Footer.scss";
import logo from "../../assets/logo/cartagenita_stories_logo.svg";
import CCBYSA from "../../assets/logo/CC_BY-SA_icon.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer__container">
        <div className="footer__section footer__branding">
          <a href="/">
            <img src={logo} alt="Logo" className="footer__logo" />
          </a>
          <div className="creative-commons-container">
            <a
              href="https://creativecommons.org/licenses/by-sa/4.0/"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img src={CCBYSA} alt="ccbysa" className="creative-commons-img" />
            </a>
            <p className="creative-commons-text">
              Cartagenita Stories © 2024 by Lucía Martínez Abellán is licensed
              under Creative Commons Attribution-ShareAlike 4.0 International.
              To view a copy of this license, visit
              <a
                href="https://creativecommons.org/licenses/by-sa/4.0/"
                target="_blank"
                rel="noopener noreferrer"
              >
                Creative Commons Attribution-ShareAlike 4.0
              </a>
              .
            </p>
          </div>
        </div>

        <div className="footer__section">
          <h4>Nuestros Cuentos</h4>
          <ul>
            <li>Isaac Peral y sus amigos explorando el mar</li>
            <li>El Faro Mágico de Cabo de Palos</li>
            <li>La ballena Elorrieta se va de vacaciones</li>
            <li>El tesoro escondido del Teatro Romano</li>
            <li>El Ritmo de las Olas y el Jazz del Mar Menor</li>
            <li>Papá Noel y el Faro de Navidad</li>
          </ul>
        </div>

        <div className="footer__section">
          <h4 id="contacto">Contacto</h4>
          <ul>
            <li>Email: support@cartagenitastories.es</li>
            <li>
              <a href="/docs/condiciones.pdf">Términos y condiciones</a>
            </li>
            <li>
              <a href="/docs/privacidad.pdf">Política de privacidad</a>
            </li>
            <li>
              <a href="/docs/cookies.pdf">Política de cookies</a>
            </li>
            <li>
              <a href="/docs/devoluciones.pdf">Política de devoluciones</a>
            </li>
          </ul>
        </div>

        <div className="footer__section footer__socials">
          <h4>Síguenos en</h4>
          <div className="footer__icons">
            <a
              href="https://facebook.com"
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faFacebook} />
            </a>
            <a href="https://instagram.com">
              <FontAwesomeIcon icon={faInstagram} />
            </a>
            <a href="https://twitter.com">
              <FontAwesomeIcon icon={faTwitter} />
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
