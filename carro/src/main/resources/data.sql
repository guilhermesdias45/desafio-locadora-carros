-- Insert Fabricantes (Manufacturers)
INSERT INTO fabricante (nome) VALUES ('Volkswagen');
INSERT INTO fabricante (nome) VALUES ('Toyota');
INSERT INTO fabricante (nome) VALUES ('Ford');

-- Insert ModeloCarro (Car Models)
-- Volkswagen: 1 model
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Gol', 'HATCH_COMPACTO', 1);

-- Toyota: 1 model
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Corolla', 'SEDAN_MEDIO', 2);

-- Ford: 2 models
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Focus', 'HATCH_MEDIO', 3);
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Fiesta', 'HATCH_COMPACTO', 3);

-- Insert Acessorios (Accessories)
INSERT INTO acessorio (descricao) VALUES ('Ar Condicionado');
INSERT INTO acessorio (descricao) VALUES ('Direção Hidráulica');
INSERT INTO acessorio (descricao) VALUES ('Vidros Elétricos');
INSERT INTO acessorio (descricao) VALUES ('Trava Elétrica');
INSERT INTO acessorio (descricao) VALUES ('ABS');

-- Insert Carros (Cars) - distributed across models with varying accessories
-- Gol cars (modelo_carro_id = 1)
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('ABC-1234', '9BWZZZ377VT004251', 'Branco', 85.00, 1);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('DEF-5678', '9BWZZZ377VT004252', 'Preto', 90.00, 1);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('GHI-9012', '9BWZZZ377VT004253', 'Prata', 88.00, 1);

-- Corolla cars (modelo_carro_id = 2)
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('JKL-3456', 'JTNB11HK803000001', 'Azul', 120.00, 2);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('MNO-7890', 'JTNB11HK803000002', 'Vermelho', 125.00, 2);

-- Focus cars (modelo_carro_id = 3)
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('PQR-1357', 'WF0AXXWPMA0000001', 'Cinza', 110.00, 3);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('STU-2468', 'WF0AXXWPMA0000002', 'Verde', 115.00, 3);

-- Fiesta cars (modelo_carro_id = 4)
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('VWX-3690', 'MF03SXXPCM0000001', 'Amarelo', 95.00, 4);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('YZA-4812', 'MF03SXXPCM0000002', 'Roxo', 100.00, 4);
INSERT INTO carro (placa, chassi, cor, valor_diaria, modelo_carro_id) VALUES ('BCD-5924', 'MF03SXXPCM0000003', 'Laranja', 98.00, 4);

-- Insert Carro_Acessorios relationships (Many-to-Many)
-- Car 1 (Gol Branco): Ar Condicionado, Direção Hidráulica
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (1, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (1, 2);

-- Car 2 (Gol Preto): Ar Condicionado, Vidros Elétricos, Trava Elétrica
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (2, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (2, 3);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (2, 4);

-- Car 3 (Gol Prata): Direção Hidráulica, ABS
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (3, 2);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (3, 5);

-- Car 4 (Corolla Azul): Ar Condicionado, Direção Hidráulica, Vidros Elétricos, Trava Elétrica, ABS
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (4, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (4, 2);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (4, 3);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (4, 4);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (4, 5);

-- Car 5 (Corolla Vermelho): Ar Condicionado, Vidros Elétricos, ABS
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (5, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (5, 3);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (5, 5);

-- Car 6 (Focus Cinza): Direção Hidráulica, Trava Elétrica
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (6, 2);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (6, 4);

-- Car 7 (Focus Verde): Ar Condicionado, Direção Hidráulica, Vidros Elétricos
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (7, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (7, 2);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (7, 3);

-- Car 8 (Fiesta Amarelo): Vidros Elétricos, ABS
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (8, 3);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (8, 5);

-- Car 9 (Fiesta Roxo): Ar Condicionado, Trava Elétrica
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (9, 1);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (9, 4);

-- Car 10 (Fiesta Laranja): Direção Hidráulica, Vidros Elétricos, Trava Elétrica, ABS
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (10, 2);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (10, 3);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (10, 4);
INSERT INTO carro_acessorios (carro_id, acessorios_id) VALUES (10, 5);
