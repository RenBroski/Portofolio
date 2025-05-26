program Project5;

uses
  Vcl.Forms,
  Unit6 in 'Unit6.pas' {FKlinik},
  Unit1 in 'Unit1.pas' {FPasien},
  Unit2 in 'Unit2.pas' {FDokter};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFKlinik, FKlinik);
  Application.CreateForm(TFPasien, FPasien);
  Application.CreateForm(TFDokter, FDokter);
  Application.Run;
end.
