unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ExtCtrls, Vcl.DBCtrls;

type
  TForm1 = class(TForm)
    RadioGroupDaya: TDBRadioGroup;
    RadioGroupGolongan: TDBRadioGroup;
    Label1: TLabel;
    LabelPemakaian: TLabel;
    EditPemakaian: TEdit;
    LabelBiayaBeban: TLabel;
    LabelBiayaPemakaian: TLabel;
    LabelPPJU: TLabel;
    EditBiayaBeban: TEdit;
    EditBiayaPemakaian: TEdit;
    EditPPJU: TEdit;
    EditMaterai: TEdit;
    LabelMaterai: TLabel;
    EditTotalTagihan: TEdit;
    LabelTotalTagihan: TLabel;
    ButtonHitung: TButton;
    procedure RadioGroupGolonganClick(Sender: TObject);
    procedure ButtonHitungClick(Sender: TObject);
  private
    procedure UpdateDayaOptions;
    function HitungBiayaBeban(Daya: Integer; Golongan: String): Double;
    function HitungBiayaPemakaian(Daya, Pemakaian: Integer; Golongan: String): Double;
    function HitungPPJU(TotalBiayaListrik: Double): Double;
    function HitungMaterai(TotalTagihan: Double): Integer;
  public
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

procedure TForm1.RadioGroupGolonganClick(Sender: TObject);
begin
  UpdateDayaOptions;
end;

procedure TForm1.UpdateDayaOptions;
begin
  RadioGroupDaya.Items.Clear;

  case RadioGroupGolongan.ItemIndex of
    0: // Sosial
      begin
        RadioGroupDaya.Items.Add('450');
        RadioGroupDaya.Items.Add('900');
      end;
    1: // Rumah Tangga
      begin
        RadioGroupDaya.Items.Add('450');
        RadioGroupDaya.Items.Add('900');
        RadioGroupDaya.Items.Add('1300');
      end;
    2: // Bisnis
      begin
        RadioGroupDaya.Items.Add('450');
        RadioGroupDaya.Items.Add('900');
        RadioGroupDaya.Items.Add('1300');
      end;
  end;

  if RadioGroupDaya.Items.Count > 0 then
    RadioGroupDaya.ItemIndex := 0;
end;

function TForm1.HitungBiayaBeban(Daya: Integer; Golongan: String): Double;
begin
  if Golongan = 'Sosial' then
  begin
    case Daya of
      450: Result := 10000;
      900: Result := 15000;
    end;
  end
  else if Golongan = 'Rumah Tangga' then
  begin
    case Daya of
      450: Result := 11000;
      900: Result := 20000;
      1300: Result := 30100;
    end;
  end
  else if Golongan = 'Bisnis' then
  begin
    case Daya of
      450: Result := 23500;
      900: Result := 26500;
      1300: Result := 28200;
    end;
  end;

  // Menghitung biaya beban dalam satuan kVA
  Result := (Daya / 1000) * Result;
end;

function TForm1.HitungBiayaPemakaian(Daya, Pemakaian: Integer; Golongan: String): Double;
var
  TarifPerKwh: Double;
begin
  if Golongan = 'Sosial' then
  begin
    if Daya = 450 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 123
      else if Pemakaian <= 60 then TarifPerKwh := 265
      else TarifPerKwh := 360;
    end
    else if Daya = 900 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 200
      else if Pemakaian <= 60 then TarifPerKwh := 295
      else TarifPerKwh := 360;
    end;
  end
  else if Golongan = 'Rumah Tangga' then
  begin
    if Daya = 450 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 169
      else if Pemakaian <= 60 then TarifPerKwh := 360
      else TarifPerKwh := 495;
    end
    else if Daya = 900 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 275
      else if Pemakaian <= 60 then TarifPerKwh := 445
      else TarifPerKwh := 495;
    end
    else if Daya = 1300 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 385
      else if Pemakaian <= 60 then TarifPerKwh := 445
      else TarifPerKwh := 495;
    end;
  end
  else if Golongan = 'Bisnis' then
  begin
    if Daya = 450 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 254
      else if Pemakaian <= 60 then TarifPerKwh := 420
      else TarifPerKwh := 470;
    end
    else if Daya = 900 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 420
      else if Pemakaian <= 60 then TarifPerKwh := 465
      else TarifPerKwh := 515;
    end
    else if Daya = 1300 then
    begin
      if Pemakaian <= 30 then TarifPerKwh := 470
      else if Pemakaian <= 60 then TarifPerKwh := 473
      else TarifPerKwh := 523;
    end;
  end;

  Result := Pemakaian * TarifPerKwh;
end;

function TForm1.HitungPPJU(TotalBiayaListrik: Double): Double;
begin
  // Menghitung PPJU sebesar 3% dari total biaya listrik
  Result := TotalBiayaListrik * 0.03;
end;

function TForm1.HitungMaterai(TotalTagihan: Double): Integer;
begin
  if TotalTagihan < 250000 then
    Result := 0
  else if TotalTagihan < 1000000 then
    Result := 3000
  else
    Result := 6000;
end;

procedure TForm1.ButtonHitungClick(Sender: TObject);
var
  Daya, Pemakaian: Integer;
  Golongan: String;
  BiayaBeban, BiayaPemakaian, PPJU, TotalTagihan: Double;
  Materai: Integer;
begin
  Daya := StrToInt(RadioGroupDaya.Items[RadioGroupDaya.ItemIndex]);
  Golongan := RadioGroupGolongan.Items[RadioGroupGolongan.ItemIndex];
  Pemakaian := StrToInt(EditPemakaian.Text);

  BiayaBeban := HitungBiayaBeban(Daya, Golongan);
  EditBiayaBeban.Text := FloatToStr(BiayaBeban);

  BiayaPemakaian := HitungBiayaPemakaian(Daya, Pemakaian, Golongan);
  EditBiayaPemakaian.Text := FloatToStr(BiayaPemakaian);

  PPJU := HitungPPJU(BiayaBeban + BiayaPemakaian);
  EditPPJU.Text := FloatToStr(PPJU);

  TotalTagihan := BiayaBeban + BiayaPemakaian + PPJU;
  Materai := HitungMaterai(TotalTagihan);
  EditMaterai.Text := IntToStr(Materai);

  TotalTagihan := TotalTagihan + Materai;
  EditTotalTagihan.Text := FloatToStr(TotalTagihan);
end;

end.

