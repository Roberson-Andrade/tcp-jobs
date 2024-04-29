PGDMP     5                    |            sistemasDistribuidos    15.3    15.3 "    (           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            )           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            *           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            +           1262    25928    sistemasDistribuidos    DATABASE     �   CREATE DATABASE "sistemasDistribuidos" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
 &   DROP DATABASE "sistemasDistribuidos";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            ,           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    25929 	   Candidato    TABLE     �   CREATE TABLE public."Candidato" (
    "idCandidato" integer NOT NULL,
    nome character varying(30) NOT NULL,
    email character varying(50) NOT NULL,
    senha integer NOT NULL
);
    DROP TABLE public."Candidato";
       public         heap    postgres    false    4            �            1259    25946    CandidatoCompetencia    TABLE     �   CREATE TABLE public."CandidatoCompetencia" (
    "idCandidatoCompetencia" integer NOT NULL,
    "idCandidato" integer NOT NULL,
    "idCompetencia" integer NOT NULL,
    tempo integer NOT NULL
);
 *   DROP TABLE public."CandidatoCompetencia";
       public         heap    postgres    false    4            �            1259    26003    CandidatoVaga    TABLE     �   CREATE TABLE public."CandidatoVaga" (
    "idCandidatoVaga" integer NOT NULL,
    "idCandidato" integer NOT NULL,
    "idVaga" integer NOT NULL,
    visualizou boolean
);
 #   DROP TABLE public."CandidatoVaga";
       public         heap    postgres    false    4            �            1259    25941    Competencia    TABLE     |   CREATE TABLE public."Competencia" (
    "idCompetencia" integer NOT NULL,
    competencia character varying(50) NOT NULL
);
 !   DROP TABLE public."Competencia";
       public         heap    postgres    false    4            �            1259    25934    Empresa    TABLE     �   CREATE TABLE public."Empresa" (
    "idEmpresa" integer NOT NULL,
    "razaoSocial" character varying(30),
    email character varying(50) NOT NULL,
    senha integer NOT NULL,
    ramo character varying(255),
    descricao text
);
    DROP TABLE public."Empresa";
       public         heap    postgres    false    4            �            1259    25961    Vaga    TABLE     �   CREATE TABLE public."Vaga" (
    "idVaga" integer NOT NULL,
    "idEmpresa" integer NOT NULL,
    "faixaSalarial" real,
    descricao text
);
    DROP TABLE public."Vaga";
       public         heap    postgres    false    4            �            1259    25988    VagaCompetencia    TABLE     �   CREATE TABLE public."VagaCompetencia" (
    "idVagaCompetencia" integer NOT NULL,
    "idVaga" integer NOT NULL,
    tempo integer NOT NULL,
    "idCompetencia" integer NOT NULL
);
 %   DROP TABLE public."VagaCompetencia";
       public         heap    postgres    false    4                      0    25929 	   Candidato 
   TABLE DATA           H   COPY public."Candidato" ("idCandidato", nome, email, senha) FROM stdin;
    public          postgres    false    214   &)       "          0    25946    CandidatoCompetencia 
   TABLE DATA           q   COPY public."CandidatoCompetencia" ("idCandidatoCompetencia", "idCandidato", "idCompetencia", tempo) FROM stdin;
    public          postgres    false    217   C)       %          0    26003    CandidatoVaga 
   TABLE DATA           a   COPY public."CandidatoVaga" ("idCandidatoVaga", "idCandidato", "idVaga", visualizou) FROM stdin;
    public          postgres    false    220   `)       !          0    25941    Competencia 
   TABLE DATA           E   COPY public."Competencia" ("idCompetencia", competencia) FROM stdin;
    public          postgres    false    216   })                  0    25934    Empresa 
   TABLE DATA           ^   COPY public."Empresa" ("idEmpresa", "razaoSocial", email, senha, ramo, descricao) FROM stdin;
    public          postgres    false    215   �)       #          0    25961    Vaga 
   TABLE DATA           S   COPY public."Vaga" ("idVaga", "idEmpresa", "faixaSalarial", descricao) FROM stdin;
    public          postgres    false    218   �)       $          0    25988    VagaCompetencia 
   TABLE DATA           b   COPY public."VagaCompetencia" ("idVagaCompetencia", "idVaga", tempo, "idCompetencia") FROM stdin;
    public          postgres    false    219   �)       �           2606    25950 .   CandidatoCompetencia CandidatoCompetencia_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."CandidatoCompetencia"
    ADD CONSTRAINT "CandidatoCompetencia_pkey" PRIMARY KEY ("idCandidatoCompetencia");
 \   ALTER TABLE ONLY public."CandidatoCompetencia" DROP CONSTRAINT "CandidatoCompetencia_pkey";
       public            postgres    false    217            �           2606    26007     CandidatoVaga CandidatoVaga_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."CandidatoVaga"
    ADD CONSTRAINT "CandidatoVaga_pkey" PRIMARY KEY ("idCandidatoVaga");
 N   ALTER TABLE ONLY public."CandidatoVaga" DROP CONSTRAINT "CandidatoVaga_pkey";
       public            postgres    false    220            }           2606    25933    Candidato Candidato_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public."Candidato"
    ADD CONSTRAINT "Candidato_pkey" PRIMARY KEY ("idCandidato");
 F   ALTER TABLE ONLY public."Candidato" DROP CONSTRAINT "Candidato_pkey";
       public            postgres    false    214            �           2606    25945    Competencia Competencia_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public."Competencia"
    ADD CONSTRAINT "Competencia_pkey" PRIMARY KEY ("idCompetencia");
 J   ALTER TABLE ONLY public."Competencia" DROP CONSTRAINT "Competencia_pkey";
       public            postgres    false    216                       2606    25940    Empresa Empresa_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Empresa"
    ADD CONSTRAINT "Empresa_pkey" PRIMARY KEY ("idEmpresa");
 B   ALTER TABLE ONLY public."Empresa" DROP CONSTRAINT "Empresa_pkey";
       public            postgres    false    215            �           2606    25992 $   VagaCompetencia VagaCompetencia_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public."VagaCompetencia"
    ADD CONSTRAINT "VagaCompetencia_pkey" PRIMARY KEY ("idVagaCompetencia");
 R   ALTER TABLE ONLY public."VagaCompetencia" DROP CONSTRAINT "VagaCompetencia_pkey";
       public            postgres    false    219            �           2606    25967    Vaga Vaga_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public."Vaga"
    ADD CONSTRAINT "Vaga_pkey" PRIMARY KEY ("idVaga");
 <   ALTER TABLE ONLY public."Vaga" DROP CONSTRAINT "Vaga_pkey";
       public            postgres    false    218            �           2606    25951     CandidatoCompetencia idCandidato    FK CONSTRAINT     �   ALTER TABLE ONLY public."CandidatoCompetencia"
    ADD CONSTRAINT "idCandidato" FOREIGN KEY ("idCandidato") REFERENCES public."Candidato"("idCandidato");
 N   ALTER TABLE ONLY public."CandidatoCompetencia" DROP CONSTRAINT "idCandidato";
       public          postgres    false    217    3197    214            �           2606    26008    CandidatoVaga idCandidato    FK CONSTRAINT     �   ALTER TABLE ONLY public."CandidatoVaga"
    ADD CONSTRAINT "idCandidato" FOREIGN KEY ("idCandidato") REFERENCES public."Candidato"("idCandidato");
 G   ALTER TABLE ONLY public."CandidatoVaga" DROP CONSTRAINT "idCandidato";
       public          postgres    false    220    214    3197            �           2606    25956 "   CandidatoCompetencia idCompetencia    FK CONSTRAINT     �   ALTER TABLE ONLY public."CandidatoCompetencia"
    ADD CONSTRAINT "idCompetencia" FOREIGN KEY ("idCompetencia") REFERENCES public."Competencia"("idCompetencia");
 P   ALTER TABLE ONLY public."CandidatoCompetencia" DROP CONSTRAINT "idCompetencia";
       public          postgres    false    3201    216    217            �           2606    25998    VagaCompetencia idCompetencia    FK CONSTRAINT     �   ALTER TABLE ONLY public."VagaCompetencia"
    ADD CONSTRAINT "idCompetencia" FOREIGN KEY ("idCompetencia") REFERENCES public."Competencia"("idCompetencia");
 K   ALTER TABLE ONLY public."VagaCompetencia" DROP CONSTRAINT "idCompetencia";
       public          postgres    false    216    219    3201            �           2606    25968    Vaga idEmpresa    FK CONSTRAINT     �   ALTER TABLE ONLY public."Vaga"
    ADD CONSTRAINT "idEmpresa" FOREIGN KEY ("idEmpresa") REFERENCES public."Empresa"("idEmpresa");
 <   ALTER TABLE ONLY public."Vaga" DROP CONSTRAINT "idEmpresa";
       public          postgres    false    218    3199    215            �           2606    25993    VagaCompetencia idVaga    FK CONSTRAINT     �   ALTER TABLE ONLY public."VagaCompetencia"
    ADD CONSTRAINT "idVaga" FOREIGN KEY ("idVaga") REFERENCES public."Vaga"("idVaga");
 D   ALTER TABLE ONLY public."VagaCompetencia" DROP CONSTRAINT "idVaga";
       public          postgres    false    3205    218    219            �           2606    26013    CandidatoVaga idVaga    FK CONSTRAINT        ALTER TABLE ONLY public."CandidatoVaga"
    ADD CONSTRAINT "idVaga" FOREIGN KEY ("idVaga") REFERENCES public."Vaga"("idVaga");
 B   ALTER TABLE ONLY public."CandidatoVaga" DROP CONSTRAINT "idVaga";
       public          postgres    false    3205    220    218                  x������ � �      "      x������ � �      %      x������ � �      !      x������ � �             x������ � �      #      x������ � �      $      x������ � �     