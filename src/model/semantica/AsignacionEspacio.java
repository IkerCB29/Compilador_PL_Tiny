package model.semantica;

import java.io.IOException;
import model.Procesamiento;
import model.sintaxis.SintaxisAbstracta.*;
import view.Printer;

public class AsignacionEspacio implements Procesamiento {


	private final Printer output;
	private int dir;
	private int max_dir;
	private int dir_ant;
	private int max_dir_ant;
	private int nivel;

	public AsignacionEspacio(Printer output) {
		this.output = output;
		dir = 0;
		max_dir = 0;
		nivel = 0;
	}

    @Override
    public void procesa(Prog prog) throws IOException {
		prog.bloque().procesa(this);
    }

    @Override
    public void procesa(Bloque bloque) throws IOException {
		bloque.decsOpt().procesa(this);
		bloque.instrsOpt().procesa(this);
    }

    @Override
    public void procesa(Si_decs decs) throws IOException {
		preprocesa(decs.decs());
        decs.decs().procesa(this);
    }

    @Override
    public void procesa(No_decs decs) throws IOException { }

	private void preprocesa(Decs decs) throws IOException {
        if(claseDe(decs, L_decs.class)){
            preprocesa(decs.decs());
        }
        preprocesa(decs.dec());
    }

	private void preprocesa(Dec dec) throws IOException {
        if(claseDe(dec, T_dec.class)){
            preprocesa(dec.tipo());
        }
        else if(claseDe(dec, V_dec.class)){
			preprocesa(dec.tipo());
			dec.setDir(this.dir);
			dec.setNivel(this.nivel);
			inc_dir(dec.tipo().getTam());
        }
        else{
			dec.setDirAnt(dir);
			dec.setMaxDirAnt(max_dir);  
			this.nivel++;
			dec.setNivel(nivel);
			dir = 0;
			max_dir = 0; 
			preprocesa(dec.lParamOpt()); 
			dec.lParamOpt().procesa(this);
			dec.bloque().procesa(this);
			dec.setDir(dir); 
			dir = dec.getDirAnt();
			max_dir = dec.getMaxDirAnt();
			nivel--;
        }
    }

	private void preprocesa(Tipo tipo) throws IOException {
        if(claseDe(tipo, A_tipo.class)){
            preprocesa(tipo);
            tipo.setTam(tipo.getTam() * Integer.parseInt(tipo.capacidad()));
        }
        else if(claseDe(tipo, P_tipo.class)) {
            if(!claseDe(tipo.tipo(), Id_tipo.class)){
                preprocesa(tipo.tipo());
            }
			tipo.setTam(1);
        }
        else if(claseDe(tipo, Id_tipo.class)) {
            if(!claseDe(tipo.getVinculo(), T_dec.class))
                output.write("El identificador " + tipo.iden() + " no esta vinculado a un declaracion "
                    + "de tipo\n");
            ((Id_tipo)tipo).setVinculo(tipo.getVinculo());
			tipo.setTam(tipo.getVinculo().getTam());
        }
        else if(claseDe(tipo, Struct_tipo.class)){
            preprocesa(tipo.campos());
            tipo.setTam(tipo.campos().getTam());
        }
		else{
			tipo.setTam(1);
		}
    }

	private void preprocesa(Campos campos) throws IOException{
		if(claseDe(campos, L_campos.class)){
			preprocesa(campos.campos());
			preprocesa(campos.campo(), campos.getTam());
			campos.setTam(campos.campos().getTam() + campos.campo().getTam());
		}
		else{
			preprocesa(campos.campo(), 0);
			campos.setTam(campos.campo().getTam());
		}	
	}

	private void preprocesa(Campo campo, int desp) throws IOException{
		preprocesa(campo.tipo());
		campo.setTam(campo.tipo().getTam());
		campo.setDesp(desp);
	}

	private void preprocesa(LParam_opt lParam) throws IOException{
        if(claseDe(lParam, Si_param.class)){
            preprocesa(lParam.lParam());
        }
    }

    private void preprocesa(LParam lParam) throws IOException{
        if(claseDe(lParam, L_param.class)){
            preprocesa(lParam.lParam());
            preprocesa(lParam.param());
        }
        else{
            preprocesa(lParam.param());
        }
    }

    private void preprocesa(Param param) throws IOException{
		preprocesa(param.tipo());
		param.setDir(dir);
		param.setNivel(nivel);
		inc_dir(param.tipo().getTam());
    }

	@Override
    public void procesa(L_decs decs) throws IOException {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
    }

    @Override
    public void procesa(Una_dec decs) throws IOException {
        decs.dec().procesa(this);
    }

	@Override
    public void procesa(T_dec dec) throws IOException {
        dec.tipo().procesa(this);
    }

    @Override
    public void procesa(V_dec dec) throws IOException {
        dec.tipo().procesa(this);
    }

    @Override
    public void procesa(P_dec dec) throws IOException {}

    @Override
    public void procesa(A_tipo tipo) throws IOException {
		tipo.tipo().procesa(this);
    }

    @Override
    public void procesa(P_tipo tipo) throws IOException {
		if(claseDe(tipo.tipo(), Id_tipo.class)){
            tipo.tipo().setVinculo(tipo.getVinculo());
            if(!claseDe(tipo.tipo().getVinculo(), T_dec.class))
                output.write("El identificador " + tipo.iden() + " no esta vinculado a un declaracion "
                    + "de tipo\n");
        }
        else {
            tipo.tipo().procesa(this);
        }
    }

    @Override
    public void procesa(In_tipo tipo) throws IOException {}

    @Override
    public void procesa(R_tipo tipo) throws IOException {}

    @Override
    public void procesa(B_tipo tipo) throws IOException {}

    @Override
    public void procesa(String_tipo tipo) throws IOException {}

    @Override
    public void procesa(Id_tipo tipo) throws IOException {
		if(!claseDe(tipo.getVinculo(), T_dec.class))
			output.write("El identificador " + tipo.iden() + " no esta vinculado a un declaracion "
				+ "de tipo\n");
		((Id_tipo)tipo).setVinculo(tipo.getVinculo());
		tipo.setTam(tipo.getVinculo().getTam());

    }

    @Override
    public void procesa(Struct_tipo tipo) throws IOException {
		tipo.campos().procesa(this);
    }

    @Override
    public void procesa(L_campos campos) throws IOException {
		campos.campos().procesa(this);
		campos.campo().procesa(this);
    }

    @Override
    public void procesa(Un_campo campos) throws IOException {
		campos.campo().procesa(this);
    }

    @Override
    public void procesa(Camp campo) throws IOException {
		campo.tipo().procesa(this);
    }

    @Override
    public void procesa(Si_param lParam) throws IOException {
		lParam.lParam().procesa(this);
    }

    @Override
    public void procesa(No_param lParam) throws IOException {}

    @Override
    public void procesa(L_param lParam) throws IOException {
		lParam.lParam().procesa(this);
		lParam.param().procesa(this);
    }

    @Override
    public void procesa(Un_param lParam) throws IOException {
		lParam.param().procesa(this);
    }

    @Override
    public void procesa(Param_simple param) throws IOException {
		param.tipo().procesa(this);
    }

    @Override
    public void procesa(Param_ref param) throws IOException {
		param.tipo().procesa(this);
    }

    @Override
    public void procesa(Si_instrs instrs) throws IOException {
		instrs.instrs().procesa(this);
    }

    @Override
    public void procesa(No_instrs instrs) throws IOException {}

    @Override
    public void procesa(L_instrs instrs) throws IOException {
		instrs.instrs().procesa(this);
		instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Una_instr instrs) throws IOException {
		instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Eva instr) throws IOException {}

    @Override
    public void procesa(If_instr instr) throws IOException {
		instr.setDirAnt(dir);
		instr.bloque().procesa(this);
		dir = instr.getDirAnt();
    }

    @Override
    public void procesa(If_el instr) throws IOException {
		instr.setDirAnt(dir);
		instr.bloque().procesa(this);
		dir = instr.getDirAnt();
		instr.bloqueElse().procesa(this);
		dir = instr.getDirAnt();
    }

    @Override
    public void procesa(Wh instr) throws IOException {
		instr.setDirAnt(dir);
		instr.bloque().procesa(this);
		dir = instr.getDirAnt();
    }

    @Override
    public void procesa(Rd instr) throws IOException { }

    @Override
    public void procesa(Wr instr) throws IOException {}

    @Override
    public void procesa(Nw instr) throws IOException {}

    @Override
    public void procesa(Dl instr) throws IOException {}

    @Override
    public void procesa(Nl_instr instr) throws IOException {}

    @Override
    public void procesa(Cl instr) throws IOException {}

    @Override
    public void procesa(Bq_instr instr) throws IOException {
		instr.setDirAnt(dir);
		instr.bloque().procesa(this);
		dir = instr.getDirAnt();
    }

	private void inc_dir(int tam) {
		dir += tam;
		if(dir > max_dir)
			max_dir = dir;
	}

    @Override
    public void procesa(Si_exps exps) throws IOException {

    }

    @Override
    public void procesa(No_exps exps) throws IOException {

    }

    @Override
    public void procesa(L_exps exps) throws IOException {

    }

    @Override
    public void procesa(Una_exp exps) throws IOException {

    }

    @Override
    public void procesa(Asig exp) throws IOException {

    }

    @Override
    public void procesa(My exp) throws IOException {

    }

    @Override
    public void procesa(Mn exp) throws IOException {

    }

    @Override
    public void procesa(Myig exp) throws IOException {

    }

    @Override
    public void procesa(Mnig exp) throws IOException {

    }

    @Override
    public void procesa(Ig exp) throws IOException {

    }

    @Override
    public void procesa(Dif exp) throws IOException {

    }

    @Override
    public void procesa(Suma exp) throws IOException {

    }

    @Override
    public void procesa(Resta exp) throws IOException {

    }

    @Override
    public void procesa(And exp) throws IOException {

    }

    @Override
    public void procesa(Or exp) throws IOException {

    }

    @Override
    public void procesa(Mul exp) throws IOException {

    }

    @Override
    public void procesa(Div exp) throws IOException {

    }

    @Override
    public void procesa(Mod exp) throws IOException {

    }

    @Override
    public void procesa(Menos_unario exp) throws IOException {

    }

    @Override
    public void procesa(Not exp) throws IOException {

    }

    @Override
    public void procesa(Indexacion exp) throws IOException {

    }

    @Override
    public void procesa(Acceso exp) throws IOException {

    }

    @Override
    public void procesa(Indireccion exp) throws IOException {

    }

    @Override
    public void procesa(Entero exp) throws IOException {

    }

    @Override
    public void procesa(Real exp) throws IOException {

    }

    @Override
    public void procesa(True exp) throws IOException {

    }

    @Override
    public void procesa(False exp) throws IOException {

    }

    @Override
    public void procesa(String_exp exp) throws IOException {

    }

    @Override
    public void procesa(Iden exp) throws IOException {

    }

    @Override
    public void procesa(Null_exp exp) throws IOException {

    }

	private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }
}
