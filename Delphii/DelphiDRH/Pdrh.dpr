program Pdrh;

uses
  Vcl.Forms,
  Udrh in 'Udrh.pas' {FDRH};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFDRH, FDRH);
  Application.Run;
end.
